import '../CSS/reset.css'
import '../CSS/BodyStyle.css'
import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'

function Home() {

  const UrlGetList = "http://localhost:8080/planta/ListarPlantas"

  const [listaAreas, setListaAreas] = useState([]);


  const getListaAreas = async () => {
    try {
      const response = await fetch(UrlGetList);
      const data = await response.json();
      setListaAreas(data);
    } catch (error) {
      console.error('Erro ao buscar lista de subáreas:', error);
    }
  };

  useEffect(() => {
    getListaAreas();
  }, []);


return (
    <div className='BoxHome'>
                  
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
                  {listaAreas.map((data, i)=>{return(<>
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
                </table>


      </div>
    
  );
}

export default Home; 