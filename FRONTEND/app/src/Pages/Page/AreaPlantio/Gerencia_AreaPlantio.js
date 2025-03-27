import '../../CSS/BodyStyle.css'
import React, { useState, useEffect } from 'react';
import AdubacaoAreaPlantio from '../AreaPlantio/AdubacaoArea'
import RelatorioArea from './RelatorioArea'
import { useNavigate } from 'react-router-dom';

function Gerencia_AreaPlantio() {

  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState(null);

  const handleOpenModal = (content) => {
      setModalContent(content);
      setShowModal(true);
  };

  const UrlGetList = "http://localhost:8080/areaPlantio/ListarAreas"
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

  const navigate = useNavigate();

  const [dataRequest, setDataRequest] = useState({
      'nomeIdentificador': '',
      'codigo': '',
      'dimensao': '',
      'gps': '',
      'notificacoes': ''
  })

  const handleRowSelect = (data) => {
    setDataRequest({
      'nomeIdentificador': data.nomeIdentificador,
      'codigo': data.codigo,
      'dimensao': data.dimencao,
      'gps': data.gps,
      'notificacoes': data.notificacoes
    });
  }

  return (
    <>
       <table class="table">
                      <thead>
                        <tr>
                          <th scope="col">Nomee Identificador</th>
                          <th scope="col">Código</th>
                          <th scope="col">Dimensão</th>
                          <th scope="col">GPS</th>
                        </tr>
                      </thead>
       
       {listaAreas.map((data, i)=>{return(<>
                  
                      <tbody>
                      <tr>
                        <th scope="row">{data.nomeIdentificador}</th>
                        <td>{data.codigo}</td>
                        <td>{data.dimencao}</td>
                        <td>{data.gps}</td>
                        <td><a className='opcaoExtra' onClick={() => handleOpenModal('Adubacao')} >Adubação</a></td>
                        <td><a className='opcaoExtra' onClick={() => { handleRowSelect(data); handleOpenModal('teste'); }}>Relatório</a></td>
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
                            {modalContent === 'Adubacao' && <AdubacaoAreaPlantio data={dataRequest}/>}
                            {modalContent === 'teste' && <RelatorioArea data={dataRequest}/>}
                        </div>
                    </div>
                </div>
                )} 
    </>
  );
}

export default Gerencia_AreaPlantio;