import React, { useState } from 'react';
import { data, useNavigate } from 'react-router-dom';

function Edit_AreaPlantio({data}) {

  const UrlPut = "http://localhost:8080/areaPlantio/EditarAreaPlantio"
  const navigate = useNavigate();


  const [dataPost, serdataPost] = useState({
    nomeIdentificador: data.nomeIdentificador,
    dimensao: data.dimensao,
    gps: data.gps,
    eixoX: data.eixoX,
    eixoY: data.eixoY
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
          gps: dataPost.gps,
          eixoX: parseInt(dataPost.eixoX),
          eixoY: parseInt(dataPost.eixoY)
    })})
    .then(navigate("/gerenciar")) 
    .then({
      nomeIdentificador: data.nomeIdentificador,
      dimensao: data.dimensao,
      gps: data.gps,
      eixoX: data.eixoX,
      eixoY: data.eixoY
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
                <td>
                  <div className="input-group mb-3">
                    <button className="btn btn-outline-secondary" type="button" id="button-addon1">GPS</button>
                    <input type="text" name="gps" value={dataPost.gps} onChange={handleChanage} className="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                  </div>
                </td>
              </tr>
              <tr>
                <td>
                  <div className="input-group mb-3">
                    <button className="btn btn-outline-secondary" type="button" id="button-addon1">Eixo X</button>
                    <input type="number" name="eixoX" value={dataPost.eixoX} onChange={handleChanage} className="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                  </div>
                </td>
              </tr>
              <tr>
                <td>
                  <div className="input-group mb-3">
                    <button className="btn btn-outline-secondary" type="button" id="button-addon1">Eixo Y</button>
                    <input type="number" name="eixoY" value={dataPost.eixoY} onChange={handleChanage} className="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
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