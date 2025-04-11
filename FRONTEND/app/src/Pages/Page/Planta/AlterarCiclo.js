import React, { useState } from 'react';
import '../../CSS/BodyStyle.css'
import { useNavigate } from 'react-router-dom';


function AlterarCiclo({ data }) {
    const UrlPut = "http://localhost:8080/Planta/AlterarCiclo"
    const [alterarLocalizacao, setalterarLocalizacao ] = useState('')
    const navigate = useNavigate();
    const [dataPost, setDataPost] = useState({
      localizacao: data.localizacao,
      ciclo: '',
      id: Number(data.idPlanta),
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
            id: dataPost.id,
            ciclo: dataPost.ciclo
          })
        })
        .then(navigate("/gerenciar")) 
        setDataPost({
          localizacao: data.localizacao,
          ciclo: '',
          id: Number(data.idPlanta),
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
                        <br/>
                          <div class="input-group mb-3">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon1">Área Plantio</button>
                            <input type="text" value={data.areaPlantio}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
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
                        name="ciclo"
                        value={dataPost.ciclo}
                        onChange={handleChanage}
                      >
                        <option value="">Selecione a opção desejada</option>
                        <option value="GERMINACAO">Germinação</option>
                        <option value="MUDA">Muda</option>
                        <option value="CRESCIMENTO">Crescimento</option>
                        <option value="FLORACAO">Floração</option>
                        <option value="FRUTIFICACAO">Frutificação</option>
                        <option value="MATURACAO">Maturação</option>
                        <option value="FIM">Fim de Ciclo</option>
                      </select>
                      <br/>
                    </td>
                  </tr>
                  <tr>
                    <td><button type="submit" onClick={handleClick} class="btn btn-success">Salvar</button></td>
                  </tr>
                </table>
              </form>
              
            </div>
          </div> 

  
      </>
    );
  }
  
  export default AlterarCiclo;