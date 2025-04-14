import React, { useState } from 'react';
import { data, useNavigate } from 'react-router-dom';

function Edit_AreaPlantio({data}) {

  const UrlPut = "http://localhost:8080/Area/EditarArea"
  const navigate = useNavigate();

  
  const [dataPost, serdataPost] = useState({
    nomeIdentificador: data.nomeIdentificador,
    dimensao: data.dimensao,
  });

  const handleChanage = (e) => {
    const value = e.target.type === 'number' ? parseInt(e.target.value) || 0 : e.target.value;
    serdataPost(prev=>({...prev,[e.target.name]:value}));
  }
  
  const handleClick=async (e)=>{
    //e.preventDefault();
    try{
      fetch(UrlPut, {
        method: 'PUT',
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded'
        },    
        body: new URLSearchParams({
          id: data.id,
          nomeIdentificador: dataPost.nomeIdentificador,
          dimensao: dataPost.dimensao,
    })})
    .then(navigate("/gerenciar")) 
    .then({
      nomeIdentificador: data.nomeIdentificador,
      dimensao: data.dimensao,
    })
    }catch (err){
      console.log("erro")
    }
  }


  return (
    <>
      <div className="card">
        <div className="card-body">
          <form>
            <table>
              <tr>
                <td>
                  <div className="input-group mb-3">
                    <button className="btn btn-outline-secondary" type="button" id="button-addon1">Nome Identificador</button>
                    <input type="text" name="nomeIdentificador" value={dataPost.nomeIdentificador} onChange={handleChanage} className="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                  </div>
                </td>
              </tr>
              <tr>
                <td>
                  <div className="input-group mb-3">
                    <button className="btn btn-outline-secondary" type="button" id="button-addon1">Dimensão</button>
                    <input type="text" name="dimensao" value={dataPost.dimensao} onChange={handleChanage} className="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                  </div>
                </td>
              </tr>
              <tr>
                <td><button type="submit" onClick={handleClick} className="btn btn-success">Salvar</button></td>
              </tr>
            </table>
          </form>
        </div>
      </div>
    </>
  );
}

export default Edit_AreaPlantio;