import '../../CSS/BodyStyle.css'
import React, { useState, useEffect } from 'react';
import AdubacaoIndividual from './AdubacaoSubArea';

function Gerencia_SubareaPlantio() {
  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState(null);

  const handleOpenModal = (content) => {
      setModalContent(content);
      setShowModal(true);
  };

  const UrlGetList = "http://localhost:8080/subareaPlantio/ListarSubareas"
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
      'nomeIdentificador': '',
      'numeroSubarea': ''
  })

  const handleRowSelect = (data) => {
    setDataRequest({
      'nomeIdentificador': data.nomeAreaPlantio,
      'numeroSubarea': data.numero
    });
  }

  return (
    <>
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Cor</th>
                    <th scope="col">Número</th>
                    <th scope="col">Tamanho</th>
                    <th scope="col">Área de plantio</th>
                    <th scope="col">Nome Popular</th>
                    <th scope="col">Fase Atual</th>
                  </tr>
                </thead>
              
              {lista.map((data, i)=>{return(<>
                
                
                <tbody key={i}>
                  <tr>
                    <th scope="row">{data.cor}</th>
                    <td>{data.numero}</td>
                    <td>{data.tamanho}</td>
                    <td>{data.nomeAreaPlantio}</td>
                    {data.planta !== null ? (<><td>{data.planta.nomePopular}</td>
                    <td>{data.planta.faseatual}</td>
                    </>) : (<><td></td><td></td></>)}       
                    <td><a onClick={() => {handleRowSelect(data); handleOpenModal('AddAdubacao')}} className='opcaoExtra'>Adicionar Adubaçao</a></td>
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
                            {modalContent === 'AddAdubacao' && <AdubacaoIndividual data={dataRequest}/>}
                        </div>
                    </div>
                </div>
                )}
    </>
  );
}

export default Gerencia_SubareaPlantio;