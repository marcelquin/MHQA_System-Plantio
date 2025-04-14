import React, { useState, useEffect } from 'react';

function GerenciarBlocoTodos(){

  const UrlGetList = "http://localhost:8080/Bloco/ListarBlocos"
  const [lista, setLista] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState(null);  
  
  const handleOpenModal = (content) => {
    setModalContent(content);
    setShowModal(true);
};  


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

        <table class="table">
          <thead>
            <tr>
              <th scope="col">Referência</th>
              <th scope="col">Área de plantio</th>
              <th scope="col">Disponivel</th>
            </tr>
          </thead>
          {lista.map((data, i)=>{return(<>               
                <tbody key={i}>
                  <tr>
                    <td>{data.referencia}</td>
                    <td>{data.area}</td>
                    <td>{data.disponivel ? (<>Disponivel</>) : (<>Utilizado</>)}</td>       
                  </tr>
                </tbody>
              </>)})}  
           
              </table>
      
    
    </>)
};

export default GerenciarBlocoTodos