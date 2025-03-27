import '../../CSS/BodyStyle.css'
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


function AdubacaoGeral({ data }) {

  const UrlPut = "http://localhost:8080/areaPlantio/AdubacaoAreaPlantioGeral"
  const navigate = useNavigate();
  const [dataPost, setDataPost] = useState({
    nomeIdentificador: data.nomeIdentificador,
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
          nomeIdentificador: dataPost.nomeIdentificador,
          adubacao: dataPost.resumoAdubacao
    })})
    .then(navigate("/gerenciar")) 
    setDataPost({
      nomeIdentificador: data.nomeIdentificador,
      resumoAdubacao: ''
    })
    }catch (err){
      console.log("erro")
    }
  }

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
                    <br/>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="form-floating">
                      <textarea class="form-control" name='resumoAdubacao' onChange={handleChanage} placeholder="Leave a comment here" id="floatingTextarea"></textarea>
                      <label for="floatingTextarea">Resumo da Adubação</label>
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

export default AdubacaoGeral;