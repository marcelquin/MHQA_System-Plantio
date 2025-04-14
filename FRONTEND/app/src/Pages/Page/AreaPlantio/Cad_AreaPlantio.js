import React, { useState } from 'react';
import { data, useNavigate } from 'react-router-dom';

function Cad_AreaPlantio() {

  const UrlPost = "http://localhost:8080/Area/NovaArea"
  const navigate = useNavigate();


  const [dataPost, serdataPost] = useState({
    nomeIdentificador: "",
    dimensao: "",
    eixoX: 0,
    eixoY: 0,
    quantidadeBlocos: 0
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
          dimensao: dataPost.dimensao,
          gps: dataPost.gps,
          eixoX: parseInt(dataPost.eixoX),
          eixoY: parseInt(dataPost.eixoY),
          quantidadeBlocos : parseInt(dataPost.quantidadeBlocos)
        })
      })
      .then(navigate("/gerenciar")) 
      serdataPost({
        nomeIdentificador: '',
        dimensao: '',
        gps: '',
        eixoX: 0,
        eixoY: 0,
        quantidadeBlocos: 0
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
            <tr>
              <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">Dimensão</button>
                <input type="text" name="dimensao" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
            </tr>
            <tr>
              <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">Eixo X </button>
                <input type="number" name="eixoX" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
            </tr>
            <tr>
              <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">Eixo Y </button>
                <input type="number" name="eixoY" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
            </tr>
            <tr>
              <td>
              <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1">quantidade de Blocos </button>
                <input type="number" name="quantidadeBlocos" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
              </div>
              </td>
              <br/>
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