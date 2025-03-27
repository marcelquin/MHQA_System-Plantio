import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function Cad_SubareaPlantio() {

  const UrlGetList = "http://localhost:8080/areaPlantio/ListarAreas"
  const UrlPost = "http://localhost:8080/processo/NovaSubArea"
  const navigate = useNavigate();


  const [listaAreas, setListaAreas] = useState([]);

  const [dataPost, serdataPost] = useState({
    cor: "",
    tamanho: "",
    nomeAreaPlantio: "",
    quantidade: "",
  });

  const getListaAreas = async () => {
    try {
      const response = await fetch(UrlGetList);
      const data = await response.json();
      setListaAreas(data);
      console.log('Lista de subáreas:', data);
    } catch (error) {
      console.error('Erro ao buscar lista de subáreas:', error);
    }
  };

  useEffect(() => {
    getListaAreas();
  }, []);

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
          cor: dataPost.cor,
          tamanho: dataPost.tamanho,
          nomeAreaPlantio: dataPost.nomeAreaPlantio,
          quantidade: dataPost.quantidade,
    })})
    .then(navigate("/gerenciar")) 
    serdataPost({
      cor: "",
      tamanho: "",
      nomeAreaPlantio: "",
      quantidade: "",
    })
    }catch (err){
      console.log("erro")
    }
  }


  return (
    <>
        <div className="boxForm">

          <form>
              <table>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Cor</button>
                      <input type="text" name="cor" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Tamanho</button>
                      <input type="text" name="tamanho" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Quantidade</button>
                      <input type="text" name="quantidade" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                {listaAreas.map((data, i)=>{return(<>
                    <tr>
                    <td>
                      <div class="form-check">
                        <input class="form-check-input" value={data.nomeIdentificador} name="nomeAreaPlantio" onClick={handleChanage} type="checkbox" id="flexCheckDefault"/>
                        <label class="form-check-label" for="flexCheckDefault">
                        {data.nomeIdentificador} 
                        </label>
                      </div>
                      <br/>
                    </td>
                  </tr>
                  </>)})}
                <tr>
                  <td><button type="submit" onClick={handleClick} class="btn btn-success">Salvar</button></td>
                </tr>
              </table>
            </form>
        </div>
    </>
  );
}

export default Cad_SubareaPlantio;