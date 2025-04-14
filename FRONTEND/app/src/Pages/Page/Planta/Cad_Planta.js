import React, { useState, useEffect } from 'react';
import { data, useNavigate } from 'react-router-dom';


function Cad_Planta() {

  const UrlPost = "http://localhost:8080/Planta/NovaPlanta"
  const UrlGetList = "http://localhost:8080/Area/ListarAreas"
  const navigate = useNavigate();

  const getListaAreaAll = async () => {
    try {
      const response = await fetch(UrlGetList);
      const data = await response.json();
      setDadosGetAreas(data);
    } catch (error) {
      console.error('Erro ao buscar lista de áreas:', error);
    }
  };


  const [dadosGetAreas, setDadosGetAreas] = useState([])
  const [areaSelecionada, setAreaSelecionada] = useState(null);

  const [dataPost, serdataPost] = useState({
    areaId: '',
    nomeCientifico: '',
    nomePopular: '',
    instrucoes: '',
    localizacaoId: '',
    blocoId: '',
  });


  const handleChanage = (e) => {
    const { name, value } = e.target;
    serdataPost(prev => ({...prev, [name]: value}));
    
    if (name === 'areaId') {
      const area = dadosGetAreas.find(a => a.id === Number(value));
      setAreaSelecionada(area);
      // Reset localizacaoId and blocoId when area changes
      serdataPost(prev => ({
        ...prev,
        localizacaoId: '',
        blocoId: ''
      }));
    }
  }

  const handleClick = async (e) => {
    //e.preventDefault();
    try {
      fetch(UrlPost, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },    
        body: new URLSearchParams({
          areaId: Number(dataPost.areaId),
          localizacaoId: Number(dataPost.localizacaoId),
          blocoId: Number(dataPost.blocoId),
          nomeCientifico: dataPost.nomeCientifico,
          nomePopular: dataPost.nomePopular,
          instrucoes: dataPost.instrucoes,
        })
      })
      .then(() => navigate("/gerenciar")) 
      serdataPost({
        areaId: '',
        localizacaoId: '',
        blocoId: '',
        nomeCientifico: '',
        nomePopular: '',
        instrucoes: '',
      })
    } catch (err) {
      console.log("erro", err)
    }
  }

  useEffect(() => {
    getListaAreaAll();
  }, []);

 
  return (
    <>
        <div className="boxForm">
          <form onSubmit={handleClick}>
              <table>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Cientifico</button>
                      <input type="text" name="nomeCientifico" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
                      <input type="text" name="nomePopular" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                    <div class="input-group mb-3">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon1">Instruções</button>
                      <input type="text" name="instrucoes" onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>
                      <select 
                        class="form-select" 
                        aria-label="Default select example"
                        name="areaId"
                        value={dataPost.areaId || ''}
                        onChange={handleChanage}
                        >
                    {dadosGetAreas ? (<>
                      <option value="">Selecione uma área</option>
                      {dadosGetAreas.map((data, i)=>{return(<> 
                        <option key={data.id} value={data.id}>{data.nomeIdentificador}</option>
                      </>)})}
                    </>) : (<></>)}
                    </select>   
                  </td>
                  <br/>
                  <br/>
                </tr>
                <tr>
                  <td>
                      <select 
                        class="form-select" 
                        aria-label="Default select example"
                        name="localizacaoId"
                        value={dataPost.localizacaoId || ''}
                        onChange={handleChanage}
                      >
                        <option value="">Localizações disponíveis</option>
                        {areaSelecionada && areaSelecionada.localizacoes && 
                          areaSelecionada.localizacoes.map((localizacao) => (
                            localizacao.disponivel && (
                              <option key={localizacao.id} value={localizacao.id}>
                                {localizacao.referencia}
                              </option>
                            )
                          ))
                        }
                      </select>
                  </td>
                  
                </tr>
                <br/>
                <tr>
                  
                  <td>
                      <select 
                        class="form-select" 
                        aria-label="Default select example"
                        name="blocoId"
                        value={dataPost.blocoId || ''}
                        onChange={handleChanage}
                      >
                        <option value="">Blocos disponíveis</option>
                        {areaSelecionada && areaSelecionada.blocos && 
                          areaSelecionada.blocos.map((bloco) => (
                            bloco.disponivel && (
                              <option key={bloco.id} value={bloco.id}>
                                {bloco.referencia}
                              </option>
                            )
                          ))
                        }
                      </select>
                  </td>
                </tr>
                <tr>
                  
                  <td><button type="submit" class="btn btn-success">Salvar</button></td>
                </tr>
              </table>
            </form>

        </div>
    </>
  );
}

export default Cad_Planta;