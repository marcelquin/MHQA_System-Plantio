import React, { useState } from 'react';
import { data, useNavigate } from 'react-router-dom';

function Cad_AreaPlantio() {

  const UrlPost = "http://localhost:8080/areaPlantio/NovaAreaPlantio"
  const navigate = useNavigate();


  const [dataPost, serdataPost] = useState({
    nomeIdentificador: "",
    dimencao: "",
    gps: "",
    tamanhoEixoX: "",
    tamanhoEixoY: ""
  });

  const handleChanage = (e) => {
    serdataPost(prev=>({...prev,[e.target.name]:e.target.value}));
  }
  
  const handleClick=async (e)=>{
    try{
      fetch(UrlPost, {
        method: 'POST',
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded'
        },    
        body: new URLSearchParams({
          nomeIdentificador: dataPost.nomeIdentificador,
          dimencao: dataPost.dimencao,
          gps: dataPost.gps,
          tamanhoEixoX: dataPost.tamanhoEixoX,
          tamanhoEixoY: dataPost.tamanhoEixoY
    })})
    .then(navigate("/gerenciar")) 
    serdataPost({
      nomeIdentificador: "",
      dimencao: "",
      gps: "",
      tamanhoEixoX: "",
      tamanhoEixoY: ""
    })
    }catch (err){
      console.log("erro")
    }
  }


  return (
    <>

      <form>
          <table>
            <tr>
              <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Identificador</button>
                <input type="text" name="nomeIdentificador" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
            </tr>
            <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">Dimenção</button>
                <input type="text" name="dimencao" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
            <tr>
              <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">GPS</button>
                <input type="text" name="gps" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
            </tr>
            <tr>
              <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">Eixo X</button>
                <input type="number" name="tamanhoEixoX" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
            </tr>
            <tr>
              <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">Eixo Y</button>
                <input type="number" name="tamanhoEixoY" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
            </tr>
            <tr>
              <td><button type="submit" onClick={handleClick} class="btn btn-success">Salvar</button></td>
            </tr>
          </table>
        </form>
    </>
  );
}

export default Cad_AreaPlantio;