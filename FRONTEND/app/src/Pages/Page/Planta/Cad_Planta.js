import React, { useState } from 'react';
import { data, useNavigate } from 'react-router-dom';


function Cad_Planta() {

  const UrlPost = "http://localhost:8080/planta/AdicionarNovaPlanta"
  const navigate = useNavigate();

  const [dataPost, serdataPost] = useState({
    nomeCientifico: "",
    nomePopular: "",
    instrucoes: "",
    codigoSubarea: ""
  });


  const handleChanage = (e) => {
      serdataPost(prev => ({...prev, [e.target.name]: e.target.value}));
  }

  const handleClick=async (e)=>{
    try{
      fetch(UrlPost, {
        method: 'POST',
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded'
        },    
        body: new URLSearchParams({
          nomeCientifico: dataPost.nomeCientifico,
          nomePopular: dataPost.nomePopular,
          instrucoes: dataPost.instrucoes,
          codigoSubarea: dataPost.codigoSubarea
    })})
    .then(navigate("/gerenciar")) 
    serdataPost({
      nomeCientifico: "",
      nomePopular: "",
      instrucoes: "",
      codigoSubarea: ""
    })
    }catch (err){
      console.log("erro")
    }
  }

  return (
    <>
        <div className="boxForm">

          <form onSubmit={handleClick}>
              <table>
              <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Cientifico</button>
                      <input type="text" name="nomeCientifico" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
                      <input type="text" name="nomePopular" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Instruções</button>
                      <input type="text" name="instrucoes" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Localização</button>
                      <input type="text" name="codigoSubarea" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td><button type="submit" class="btn btn-success">Salvar</button></td>
                </tr>
              </table>
            </form>

        </div>
    </>
  );
}

export default Cad_Planta;