import React, { useState } from 'react';
import '../../CSS/BodyStyle.css'
import { useNavigate } from 'react-router-dom';


function AlterarCiclo({ data }) {
    const UrlPut = "http://localhost:8080/planta/AtualizaCiclo"
    const [alterarLocalizacao, setalterarLocalizacao ] = useState('')
    const navigate = useNavigate();
    const [dataPost, setDataPost] = useState({
      codigo: data.codigo,
      numeroSubarea: data.localizacao,
      novaFaseCiclo: ''
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
            numeroSubarea: dataPost.numeroSubarea,
            faseatual: dataPost.novaFaseCiclo
      })})
      .then(navigate("/gerenciar")) 
      setDataPost({
        codigo: data.codigo,
        numeroSubarea: data.numeroSubareaPlantio,
        novaFaseCiclo: ''
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
                        <div class="input-group mb-3">
                          <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
                          <input type="text" name="nomePopular" value={data.nomePopular} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                        </div>
                    </td>
                  </tr>
                  <tr>
                    <td>
                        <div class="input-group mb-3">
                          <button class="btn btn-outline-secondary" type="button" id="button-addon1">Código</button>
                          <input type="text" name="codigo" value={data.codigo} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                        </div>
                    </td>
                  </tr>
                  <tr>
                        <td>
                        <br/>
                          <div class="input-group mb-3">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon1">Localização Atual</button>
                            <input type="text" value={data.localizacao}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                          </div>
                        </td>
                      </tr>
                  <tr>
                    <td>
                      <div class="form-check">
                        <input 
                          class="form-check-input" 
                          type="checkbox" 
                          checked={alterarLocalizacao === "ok"}
                          onChange={(e) => setalterarLocalizacao(e.target.checked ? "ok" : "")} 
                          id="flexCheckIndeterminate"
                        />
                        <label class="form-check-label" for="flexCheckIndeterminate">
                          Alterar Localização
                        </label>
                      </div>
                    </td>
                  </tr>      
                    {alterarLocalizacao.length > 0 ? (<>
                      <tr>
                        <td>
                        <br/>
                          <div class="input-group mb-3">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon1">Localização</button>
                            <input type="number" name="numeroSubarea" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                          </div>
                        </td>
                      </tr>
                    </>) : (<></>)}        
                  <tr>
                    <td>
                    <br/>
                      <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Ciclo Atual</button>
                      <input type="text" value={data.faseAtual} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                      </div>
                    </td> 
                  </tr>
                  <tr>
                    <td>
                      <select 
                        class="form-select" 
                        aria-label="Default select example"
                        name="novaFaseCiclo"
                        onChange={handleChanage}
                      >
                        <option value="">Selecione a opção desejada</option>
                        <option value="G">Germinação</option>
                        <option value="M">Muda</option>
                        <option value="C">Crescimento</option>
                        <option value="FL">Floração</option>
                        <option value="FR">Frutificação</option>
                        <option value="MA">Maturação</option>
                        <option value="F">Fim de Ciclo</option>
                      </select>
                      <br/>
                    </td>
                  </tr>
                  <tr>
                    <td><button type="submit" onClick={handleClick} class="btn btn-success">Salvar</button></td>
                    <td>{dataPost.faseAtual}</td>  
                  </tr>
                </table>
              </form>
              
            </div>
          </div> 

  
      </>
    );
  }
  
  export default AlterarCiclo;