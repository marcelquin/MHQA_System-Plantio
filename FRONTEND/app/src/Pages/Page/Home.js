import '../CSS/reset.css'
import '../CSS/BodyStyle.css'
import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'

function Home() {

  const UrlGetList = "http://localhost:8080/Planta/ListarPlantas"


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
                  <th scope="col">Localização</th>
                  <th scope="col">Área</th>
                  <th scope="col">Ciclo Atual</th>
                  <th scope="col">Data de Plantio</th>
                  <th scope="col">Ciclo Anterior</th>
                  <th scope="col">Ciclo Atual</th>                  
                  <th scope="col">Última Adubação</th>
                </tr>
              </thead> 

              {pesquisaInput.length > 0 ?(<>
                {response.map((data, i)=>{return(<>
                <tbody key={i}>
                  <tr>
                    <th scope="row">{data.nomePopular}</th>
                    {data.localizacao ? (<>
                      <td>{data.localizacao && data.localizacao.referencia ? (<>{data.localizacao.referencia}</>) : (<>{data.bloco.referencia}</>)}</td>
                      <td>{data.localizacao && data.localizacao.area ? (<>{data.localizacao.area}</>) : (<>{data.bloco.area}</>)}</td>
                      </>) : (<>
                    <td>{data.bloco.referencia}</td>
                    <td>{data.bloco.area}</td>
                    </>)}
                    <td>{data.ciclo.ciclo}</td>
                    <td>{data.DataPlantio}</td>
                    <td>{data.ciclo.dataUltimoCiclo}</td>
                    <td>{data.ciclo.dataCicloAtual}</td>
                  </tr>
                </tbody>
              </>)})}
              
              </>) : (<>
              
                {listAll.map((data, i)=>{return(<>
                <tbody key={i}>
                  <tr>
                  <th scope="row">{data.nomePopular}</th>
                  {data.localizacao ? (<>
                    <td>{data.localizacao && data.localizacao.referencia ? (<>{data.localizacao.referencia}</>) : (<>{data.bloco.referencia}</>)}</td>
                    <td>{data.localizacao && data.localizacao.area ? (<>{data.localizacao.area}</>) : (<>{data.bloco.area}</>)}</td>
                    </>) : (<>
                    <td>{data.bloco.referencia}</td>
                    <td>{data.bloco.area}</td>
                    </>)}
                    <td>{data.ciclo.ciclo}</td>
                    <td>{data.DataPlantio}</td>
                    <td>{data.ciclo.dataUltimoCiclo}</td>
                    <td>{data.ciclo.dataCicloAtual}</td>
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