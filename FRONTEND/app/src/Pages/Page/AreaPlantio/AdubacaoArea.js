import '../../CSS/BodyStyle.css'
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


function AdubacaoGeral({ data }) {

  const UrlPut = "http://localhost:8080/Area/NovaAdubacao"
  const navigate = useNavigate();
  const [dataPost, setDataPost] = useState({
     relatorio: '',
     nomeIdentificador: data.nomeIdentificador,
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
          id: data.id,
          relatorio: dataPost.relatorio
    })})
    .then(navigate("/gerenciar")) 
    setDataPost({
      id: data.id,
      relatorio: ''
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
                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome</button>
                        <input type="text" value={data.nomeIdentificador}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                      </div>
                    <br/>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="form-floating">
                      <textarea class="form-control" name='relatorio' onChange={handleChanage} placeholder="Descreva a adubação" id="floatingTextarea"></textarea>
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