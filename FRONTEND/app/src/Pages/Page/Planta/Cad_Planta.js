import React, { useState } from 'react';
import { data, useNavigate } from 'react-router-dom';


function Cad_Planta() {

  const UrlPost = "http://localhost:8080/processo/NovaPlanta"
  const navigate = useNavigate();

  const [isPlantaDoadora, setIsPlantaDoadora] = useState(false);

  const [dataPost, serdataPost] = useState({
    nomeCientifico: "",
    nomePopular: "",
    instrucoes: "",
    cavalo: isPlantaDoadora,
    quantidade: ""
  });


  const handleChanage = (e) => {
    if (e.target.name === 'cavalo') {
      const isChecked = e.target.checked;
      setIsPlantaDoadora(true);
      serdataPost(prev => ({...prev, cavalo: isChecked}));
    } else {
      serdataPost(prev => ({...prev, [e.target.name]: e.target.value}));
    }
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
          cavalo: dataPost.cavalo,
          quantidade: dataPost.quantidade
    })})
    .then(navigate("/gerenciar")) 
    serdataPost({
      nomeCientifico: "",
      nomePopular: "",
      instrucoes: "",
      cavalo: isPlantaDoadora,
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
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Quantidade</button>
                      <input type="number" name="quantidade" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <div class="form-check form-switch">
                    <input class="form-check-input" name="cavalo" onChange={handleChanage} type="checkbox" id="flexSwitchCheckChecked"/>
                    <label class="form-check-label" for="flexSwitchCheckChecked">Planta Doadora</label>
                  </div>
                  <br/>
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