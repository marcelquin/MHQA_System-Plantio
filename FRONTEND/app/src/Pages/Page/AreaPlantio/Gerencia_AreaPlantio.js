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
  listAll.filter(dados => dados.nomeIdentificador.includes(pesquisaInput)) :
  []

  useEffect(() => {
    getListaAll();
  }, []);

  const navigate = useNavigate();

  const [dataRequest, setDataRequest] = useState({
      'nomeIdentificador': '',
      'codigo': '',
      'dimensao': '',
      'gps': '',
      'notificacoes': '',
      'tamanhoMax': '',
      'eixoX': '',
      'eixoY': ''
  })

  const handleRowSelect = (data) => {
    setDataRequest({
      'nomeIdentificador': data.nomeIdentificador,
      'codigo': data.codigo,
      'dimensao': data.dimencao,
      'gps': data.gps,
      'notificacoes': data.notificacoes,
      'tamanhoMax': data.maxQuantidadeSubareas,
      'eixoX': data.eixoX,
      'eixoY': data.eixoy
    });
  }

  return (
    <>
      <div class="input-group mb-3">
        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
        <input type="text" class="form-control" name="pesquisaInput" onChange={handleChange} placeholder="Digite o nome para pesquisa" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
      </div>
       <table class="table">
                      <thead>
                        <tr>
                          <th scope="col">Nomee Identificador</th>
                          <th scope="col">Código</th>
                          <th scope="col">Dimensão</th>
                          <th scope="col">GPS</th>
                        </tr>
                      </thead>
       {pesquisaInput.length > 0 ?(<>
       
        {response.map((data, i)=>{return(<>
                  
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

       </>) : (<>

        {listAll.map((data, i)=>{return(<>
                  
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
       
       </>)}
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