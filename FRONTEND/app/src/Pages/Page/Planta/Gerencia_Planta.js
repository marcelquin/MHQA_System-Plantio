import '../../CSS/BodyStyle.css'
import React, { useState, useEffect } from 'react';
import AlterarCiclo from './AlterarCiclo';
import RelatorioPlanta from './RelatorioPlanta';
function Gerencia_Planta() {

  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState(null);

  const handleOpenModal = (content) => {
      setModalContent(content);
      setShowModal(true);
  };

  const UrlGetList = "http://localhost:8080/planta/ListarPlantas"
  const [lista, setLista] = useState([]);


  const getLista = async () => {
    try {
      const response = await fetch(UrlGetList);
      const data = await response.json();
      setLista(data);
    } catch (error) {
      console.error('Erro ao buscar lista de subáreas:', error);
    }
  };

  useEffect(() => {
    getLista();
  }, []);

  const [dataRequest, setDataRequest] = useState({
    'idPlanta': '',
    'nomecientifico': '',
    'nomePopular': '',
    'codigoPlanta': '',
    'localizacao': '',
    'faseAtual': '',
    'inicioCiclo': '',
    'adubacao': '',
    'notificacoes': '',
})

const handleRowSelect = (data) => {
  setDataRequest({
    'idPlanta': data.id,
    'nomeCientifico': data.nomeCientifico,
    'nomePopular': data.nomePopular,
    'codigoPlanta': data.codigo,
    'localizacao': data.localizacao,
    'areaPlantio': data.areaPlantio,
    'faseAtual': data.faseatual,
    'inicioCiclo': data.dataPlantio,
    'adubacao': data.dataAdubacao,
    'notificacoes': data.notificacoes,
  });
}

  return (
    <>
          
               <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Inicio Ciclo</th>
                    <th scope="col">Nome Popular</th>
                    <th scope="col">Código</th>
                    <th scope="col">Ciclo Atual</th>
                    <th scope="col">Localização</th>
                    <th scope="col">Área de plantio</th>
                  </tr>
                </thead>
                {lista.map((data, i)=>{return(<>
                  <tbody key={i}>
                  <tr>
                    <th scope="row">{data.dataPlantio} </th>
                    <td>{data.nomePopular}</td>
                    <td>{data.codigo}</td>
                    <td>{data.faseatual}</td>
                    <td>{data.localizacao}</td>
                    <td>{data.areaPlantio}</td>
                    <td><a onClick={() =>{handleOpenModal('alterarCiclo'); handleRowSelect(data);} } className='opcaoExtra'>Alterar Ciclo</a></td>
                    <td><a onClick={() => {handleOpenModal('maisInfo'); handleRowSelect(data); }} className='opcaoExtra'>Mais informações</a></td>
                  </tr>
                </tbody>
                </>)})}
               
              </table>

              {showModal && (
                    <div className="modal-overlay">
                    <div className="modal-content">
                        <div className="modal-header">
                            <button 
                                className="modal-close-button"
                                onClick={() => setShowModal(false)}
                            >
                                ✕
                            </button>
                        </div>
                        <div className="modal-body">
                            {modalContent === 'alterarCiclo' && <AlterarCiclo data={dataRequest}/>}
                            {modalContent === 'maisInfo' && <RelatorioPlanta data={dataRequest}/>}
                        </div>
                    </div>
                </div>
                )}
    </>
  );
}

export default Gerencia_Planta;