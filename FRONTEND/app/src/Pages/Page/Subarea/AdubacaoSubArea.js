import { useNavigate } from 'react-router-dom';
import React, { useState } from 'react';

function AdubacaoIndividual({ data }) {
  const UrlPut = "http://localhost:8080/subareaPlantio/AdubacaoSubAreaIndividual"
  const [dataPost, setDataPost] = useState({
    codigo: data.codigo,
    resumoAdubacao: ''
  })

  const handleChanage = (e) => {
    setDataPost(prev=>({...prev,[e.target.name]:e.target.value}));
  }

  const handleClick=async (e)=>{
    try{
      fetch(UrlPut, {
        method: 'PUT',
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded'
        },    
        body: new URLSearchParams({
          codigo: dataPost.codigo,
          resumoAdubacao: dataPost.resumoAdubacao
    })})
    .then(navigate("/gerenciar")) 
    setDataPost({
    codigo: data.codigo,
    resumoAdubacao: ''
    })
    }catch (err){
      console.log("erro")
    }
  }

  const navigate = useNavigate();

    return (
      <>
        <div class="card">
          <div class="card-body">
            <form>
                <table>
                  <tr>
                    <td>
                      <br/>
                          <div class="input-group mb-3">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Identificador</button>
                            <input type="text" name="nomeIdentificador" value={data.nomeIdentificador}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                          </div>
                    </td>
                  </tr>
                  <tr>
                      <td>
                        <div class="input-group mb-3">
                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">localização</button>
                        <input type="text" value={data.codigo} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                      </div>
                    </td>
                  </tr>
                  <tr>
                      <td>
                        <div class="form-floating">
                          <textarea class="form-control" name='resumoAdubacao' onChange={handleChanage} placeholder="Resumo de Adubação" id="floatingTextarea"></textarea>
                          <label for="floatingTextarea">Resumo de Adubação</label>
                        </div>
                        <br/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <button type="submit" onClick={handleClick} class="btn btn-success">Salvar</button>
                    </td>
                  </tr>
                </table>
            </form> 
          </div>      
        </div>
     
    </>
    )
};

export default AdubacaoIndividual;
