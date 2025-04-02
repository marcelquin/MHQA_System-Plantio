import '../CSS/reset.css'
import '../CSS/BodyStyle.css'
import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'

function Home() {

  const UrlGetList = "http://localhost:8080/planta/ListarPlantas"
  const UrlGetListGerminacao = "http://localhost:8080/planta/ListarPlantasGerminacao"
  const UrlGetListMuda = "http://localhost:8080/planta/ListarPlantasMudas"
  const UrlGetListCrescimento = "http://localhost:8080/planta/ListarPlantasCrescimento"
  const UrlGetListFloracao = "http://localhost:8080/planta/ListarPlantasFloracao"
  const UrlGetListFrutificacao = "http://localhost:8080/planta/ListarPlantasFrutificacao"
  const UrlGetListMaturacao = "http://localhost:8080/planta/ListarPlantasMaturacao"
  const UrlGetLisFim = "http://localhost:8080/planta/ListarPlantasFimCiclo"

  const [listAll, setlistAll] = useState([]);
  const [pesquisaInput, setPesquisaInput] = useState('')

  const getListaAll = async () => {
    try {
      const response = await fetch(UrlGetList);
      const data = await response.json();
      setlistAll(data);
    } catch (error) {
      console.error('Erro ao buscar lista de subáreas:', error);
    }
  };

  const handleChange = (e) => {
    setPesquisaInput(e.target.value);
  }

  const response = pesquisaInput.length > 0 ?
  listAll.filter(dados => dados.nomePopular.includes(pesquisaInput)) :
  []

  useEffect(() => {
    getListaAll();
  }, []);




return (
    <div className='BoxHome'>
        <div className='boxTable'>

        <div class="input-group mb-3">
          <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
          <input type="text" class="form-control" name='pesquisaInput' onChange={handleChange} placeholder="Digite o nome para pesquisa" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
        </div>

        <table class="table">
              <thead>
                <tr>
                  <th scope="col">Nome Popular</th>
                  <th scope="col">Fase Atual</th>
                  <th scope="col">Localização</th>
                  <th scope="col">Área Plantio</th>
                  <th scope="col">Inicio de Ciclo</th>
                  <th scope="col">Última Adubação</th>
                </tr>
              </thead> 

              {pesquisaInput.length > 0 ?(<>
              
                {response.map((data, i)=>{return(<>
                <tbody key={i}>
                  <tr>
                    <th scope="row">{data.nomePopular}</th>
                    <td>{data.faseatual}</td>
                    <td>{data.localizacao}</td>
                    <td>{data.areaPlantio}</td>
                    <td>{data.dataPlantio}</td>
                    <td>{data.dataAdubacao}</td>
                  </tr>
                </tbody>
              </>)})}
              
              </>) : (<>
              
                {listAll.map((data, i)=>{return(<>
                <tbody key={i}>
                  <tr>
                    <th scope="row">{data.nomePopular}</th>
                    <td>{data.faseatual}</td>
                    <td>{data.localizacao}</td>
                    <td>{data.areaPlantio}</td>
                    <td>{data.dataPlantio}</td>
                    <td>{data.dataAdubacao}</td>
                  </tr>
                </tbody>
              </>)})}

              </>)}

              
              </table>    
        </div>
      </div>  
  );
}

export default Home; 