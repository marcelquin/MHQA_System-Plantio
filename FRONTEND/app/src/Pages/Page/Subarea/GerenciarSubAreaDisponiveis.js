import React, { useState, useEffect } from 'react';
import AdubacaoIndividual from './AdubacaoSubArea';

function GerenciarSubAreaDisponiveis(){

    const UrlGetList = "http://localhost:8080/subareaPlantio/ListarSubareasDisponiveis"
  const [lista, setLista] = useState([]);
  const [pesquisaInput, setPesquisaInput] = useState('')
  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState(null);  
  
  const handleOpenModal = (content) => {
    setModalContent(content);
    setShowModal(true);
};  

const handleChange = (e) => {
    setPesquisaInput(e.target.value);
  }

    const response = pesquisaInput.length > 0 ?
    lista.filter(dados => dados.nomeAreaPlantio.includes(pesquisaInput)) :
    []


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
      'codigo': ''
  })

  const handleRowSelect = (data) => {
    setDataRequest({
      'nomeIdentificador': data.nomeAreaPlantio,
      'codigo': data.codigo
    });
  }

    return(<>

        <div class="input-group mb-3">
            <button class="btn btn-outline-secondary" type="button" id="button-addon1">Área Plantio</button>
            <input type="text" class="form-control" name='pesquisaInput' onChange={handleChange} placeholder="Digite a área a ser pesquisa" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
        </div> 

        <table class="table">
          <thead>
            <tr>
              <th scope="col">Código</th>
              <th scope="col">Localização X</th>
              <th scope="col">Localização Y</th>
              <th scope="col">Área de plantio</th>
              <th scope="col">Nome Popular</th>
              <th scope="col">Fase Atual</th>
            </tr>
          </thead>

            {pesquisaInput.length > 0 ?(<>
            
                {response.map((data, i)=>{return(<>
                
                
                <tbody key={i}>
                  <tr>
                    <td>{data.codigo}</td>
                    <td>{data.eixoX}</td>
                    <td>{data.eixoY}</td>
                    <td>{data.nomeAreaPlantio}</td>
                    {data.planta !== null ? (<><td>{data.planta.nomePopular}</td>
                    <td>{data.planta.faseatual}</td>
                    </>) : (<><td></td><td></td></>)}       
                    <td><a onClick={() => {handleRowSelect(data); handleOpenModal('AddAdubacao')}} className='opcaoExtra'>Adicionar Adubaçao</a></td>
                  </tr>
                </tbody>
             
              
              </>)})}

            </>) : (<>
                
                {lista.map((data, i)=>{return(<>               
                <tbody key={i}>
                  <tr>
                    <td>{data.codigo}</td>
                    <td>{data.eixoX}</td>
                    <td>{data.eixoY}</td>
                    <td>{data.nomeAreaPlantio}</td>
                    {data.planta !== null ? (<><td>{data.planta.nomePopular}</td>
                    <td>{data.planta.faseatual}</td>
                    </>) : (<><td></td><td></td></>)}       
                    <td><a onClick={() => {handleRowSelect(data); handleOpenModal('AddAdubacao')}} className='opcaoExtra'>Adicionar Adubaçao</a></td>
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
                            {modalContent === 'AddAdubacao' && <AdubacaoIndividual data={dataRequest}/>}
                        </div>
                    </div>
                </div>
                )}
    
    </>)
};

export default GerenciarSubAreaDisponiveis