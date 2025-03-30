import React, { useState } from 'react';
import { data, useNavigate } from 'react-router-dom';

function EditarInfo({data}){

    const Url = "http://localhost:8080/planta/EditarInformacaoPlanta"
    const navigate = useNavigate();

    const [dataPost, serdataPost] = useState({
        id: data.idPlanta,
        nomeCientifico: data.nomeCientifico,
        nomePopular: data.nomePopular,
        instrucoes: data.instrucoes,
      });
     
    const handleChanage = (e) => {
        serdataPost(prev => ({...prev, [e.target.name]: e.target.value}));
    }
    
    const handleClick=async (e)=>{
        try{
          fetch(Url, {
            method: 'PUT',
            headers:{
              'Content-Type': 'application/x-www-form-urlencoded'
            },    
            body: new URLSearchParams({
              id: dataPost.id,
              nomeCientifico: dataPost.nomeCientifico,
              nomePopular: dataPost.nomePopular,
              instrucoes: dataPost.instrucoes
        })})
        .then(navigate("/gerenciar")) 
        serdataPost({
            id: data.idPlanta,
            nomeCientifico: data.nomeCientifico,
            nomePopular: data.nomePopular,
            instrucoes: data.instrucoes,
        })
        }catch (err){
          console.log("erro")
        }
      }

    return(<>
        <div class="card">
            <div class="card-body">
                <form>
                    <table>
                        <tr>
                            <td>
                                <div class="input-group mb-3">
                                    <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Cientifico</button>
                                    <input type="text" name="nomeCientifico" value={dataPost.nomeCientifico} onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="input-group mb-3">
                                    <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
                                    <input type="text" name="nomePopular" value={dataPost.nomePopular} onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="input-group mb-3">
                                    <button class="btn btn-outline-secondary" type="button" id="button-addon1">Orientações</button>
                                    <input type="text" name="instrucoes" value={dataPost.instrucoes} onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><button type="submit" onClick={handleClick} class="btn btn-success">Salvar</button></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

    </>)
};

export default EditarInfo;