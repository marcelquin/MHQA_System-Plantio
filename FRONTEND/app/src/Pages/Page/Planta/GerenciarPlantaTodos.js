import '../../CSS/BodyStyle.css'
import AlterarCiclo from './AlterarCiclo';
import RelatorioPlanta from './RelatorioPlanta';
import EditarInfo from './EditarInfo';
import React, { useState, useEffect } from 'react';

function GerenciarPlantaTodos(){

  const UrlGetList = "http://localhost:8080/planta/ListarPlantas"
  const [listAll, setListAll] = useState([]);
  const [pesquisaInput, setPesquisaInput] = useState('')
  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState(null);

  const handleOpenModal = (content) => {
      setModalContent(content);
      setShowModal(true);
  };


  const response = pesquisaInput.length > 0 ?
  listAll.filter(dados => dados.nomePopular.includes(pesquisaInput)) :
  []

  const handleChange = (e) => {
    setPesquisaInput(e.target.value);
  }

    const getLista = async () => {
        try {
          const response = await fetch(UrlGetList);
          const data = await response.json();
          setListAll(data);
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
        'instrucoes': '',
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
        'instrucoes': data.instrucoes,
        'localizacao': data.localizacao,
        'areaPlantio': data.areaPlantio,
        'faseAtual': data.faseatual,
        'inicioCiclo': data.dataPlantio,
        'adubacao': data.dataAdubacao,
        'notificacoes': data.notificacoes,
      });
    }

    return(<>

      <div class="input-group mb-3">
        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
        <input type="text" class="form-control" name='pesquisaInput' onChange={handleChange} placeholder="Digite o nome para pesquisa" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
        </div>  
      <table class="table">
        <thead>
            <tr>
              <th scope="col">Inicio Ciclo</th>
              <th scope="col">Nome Popular</th>
              <th scope="col">Código</th>
              <th scope="col">Ciclo Atual</th>
              <th scope="col">Localização</th>
              <th scope="col">Área de plantio</th>
              <th scope="col">Orientações</th>
            </tr>
        </thead>
        
            {pesquisaInput.length > 0 ?(<>
            
                {response.map((data, i)=>{return(<>
                  <tbody key={i}>
                  <tr>
                    <th scope="row">{data.dataPlantio} </th>
                    <td>{data.nomePopular}</td>
                    <td>{data.codigo}</td>
                    <td>{data.faseatual}</td>
                    <td>{data.localizacao}</td>
                    <td>{data.areaPlantio}</td>
                    <td>{data.instrucoes}</td>
                    <td><a onClick={() =>{handleOpenModal('alterarCiclo'); handleRowSelect(data);} } className='opcaoExtra'>Alterar Ciclo</a></td>
                    <td><a onClick={() => {handleOpenModal('Editar'); handleRowSelect(data); }} className='opcaoExtra'>Editar</a></td>
                    <td><a onClick={() => {handleOpenModal('maisInfo'); handleRowSelect(data); }} className='opcaoExtra'>Mais informações</a></td>
                  </tr>
                </tbody>
                </>)})}
            
            </>) : (<>
            
                {listAll.map((data, i)=>{return(<>
                  <tbody key={i}>
                  <tr>
                    <th scope="row">{data.dataPlantio} </th>
                    <td>{data.nomePopular}</td>
                    <td>{data.codigo}</td>
                    <td>{data.faseatual}</td>
                    <td>{data.localizacao}</td>
                    <td>{data.areaPlantio}</td>
                    <td>{data.instrucoes}</td>
                    <td><a onClick={() =>{handleOpenModal('alterarCiclo'); handleRowSelect(data);} } className='opcaoExtra'>Alterar Ciclo</a></td>
                    <td><a onClick={() => {handleOpenModal('Editar'); handleRowSelect(data); }} className='opcaoExtra'>Editar</a></td>
                    <td><a onClick={() => {handleOpenModal('maisInfo'); handleRowSelect(data); }} className='opcaoExtra'>Mais informações</a></td>
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
                            {modalContent === 'alterarCiclo' && <AlterarCiclo data={dataRequest}/>}
                            {modalContent === 'maisInfo' && <RelatorioPlanta data={dataRequest}/>}
                            {modalContent === 'Editar' && <EditarInfo data={dataRequest}/>}
                        </div>
                    </div>
                </div>
                )}
                
    </>)
};

export default GerenciarPlantaTodos;