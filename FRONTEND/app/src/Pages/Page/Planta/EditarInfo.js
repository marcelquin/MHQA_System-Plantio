import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../CSS/BodyStyle.css'

function EditarInfo({data}){

    const Url = "http://localhost:8080/Planta/EditarPlanta"
    const UrlPut = "http://localhost:8080/Planta/AlterarLocalizacao"
    const UrlGetList = "http://localhost:8080/Area/ListarAreas"

    const navigate = useNavigate();

    const [opcao, setopcao] = useState("info")
    const [dadosGetAreas, setDadosGetAreas] = useState([])
    const [areaSelecionada, setAreaSelecionada] = useState(null);

    const getListaAreaAll = async () => {
        try {
          const response = await fetch(UrlGetList);
          const data = await response.json();
          setDadosGetAreas(data);
        } catch (error) {
          console.error('Erro ao buscar lista de áreas:', error);
        }
      };

    const handleChangeOpcao = (e) => {
      setopcao(e.target.value);
    }

    const [dataPut, setdataPut] = useState({
        plantaId: data.idPlanta,
        areaId: 0,
        localizacaoId: 0,
        blocoId: 0,
    })

    const [dataPost, serdataPost] = useState({
        plantaId: data.idPlanta,
        nomeCientifico: data.nomeCientifico,
        nomePopular: data.nomePopular,
        instrucoes: data.instrucoes,
    });
     
    const handleChanage = (e) => {
        serdataPost(prev => ({...prev, [e.target.name]: e.target.value}));
    }
    
    const handleChanagePut = (e) => {
        const { name, value } = e.target;
        setdataPut(prev => ({...prev, [name]: value}));
        
        if (name === 'areaId') {
            const area = dadosGetAreas.find(a => a.id === Number(value));
            setAreaSelecionada(area);
            setdataPut(prev => ({
                ...prev,
                localizacaoId: 0,
                blocoId: 0
            }));
        } else if (name === 'localizacaoId') {
            setdataPut(prev => ({
                ...prev,
                blocoId: 0
            }));
        } else if (name === 'blocoId') {
            setdataPut(prev => ({
                ...prev,
                localizacaoId: 0
            }));
        }
    }

    const handleClick=async (e)=>{
        try{
          fetch(Url, {
            method: 'PUT',
            headers:{
              'Content-Type': 'application/x-www-form-urlencoded'
            },    
            body: new URLSearchParams({
              plantaId: Number(data.idPlanta),
              nomeCientifico: dataPost.nomeCientifico,
              nomePopular: dataPost.nomePopular,
              instrucoes: dataPost.instrucoes
            })
          })
          .then(navigate("/gerenciar")) 
          serdataPost({
              id: data.idPlanta,
              nomeCientifico: data.nomeCientifico,
              nomePopular: data.nomePopular,
              instrucoes: data.instrucoes
          })
        }catch (err){
          console.log("erro")
        }
    }
    
    const handleClickPut=async (e)=>{
        try{
          fetch(UrlPut, {
            method: 'PUT',
            headers:{
              'Content-Type': 'application/x-www-form-urlencoded'
            },    
            body: new URLSearchParams({
              plantaId: Number(data.idPlanta),
              localizacaoId: dataPut.localizacaoId,
              blocoId: Number(dataPut.blocoId)
            })
          })
          .then(navigate("/gerenciar")) 
          setdataPut({
              plantaId: data.idPlanta,
              areaId: 0,
              localizacaoId: 0,
              blocoId: 0,
          })
          serdataPost({
              id: data.idPlanta,
              nomeCientifico: data.nomeCientifico,
              nomePopular: data.nomePopular,
              instrucoes: data.instrucoes
          })
        }catch (err){
          console.log("erro")
        }
    }

    useEffect(() => {
        getListaAreaAll();
    }, []);  

    return(<>
        <div class="card">
            <div class="card-body">
                <div className='boxSelecaoEdit'>
                    <div className="form-check">
                        <input 
                        className="form-check-input" 
                        type="radio" 
                        name="opcao" 
                        id="radioFloracao" 
                        value="info"
                        checked={opcao === "info"}
                        onChange={handleChangeOpcao}
                        />
                        <label className="form-check-label" htmlFor="radioFloracao">
                        Editar Informações
                        </label>
                    </div>
                    <div className="form-check">
                        <input 
                        className="form-check-input" 
                        type="radio" 
                        name="opcao" 
                        id="radioFloracao" 
                        value="localizacao"
                        checked={opcao === "localizacao"}
                        onChange={handleChangeOpcao}
                        />
                        <label className="form-check-label" htmlFor="radioFloracao">
                        Editar Localização
                        </label>
                    </div>
                </div>
                <br/>
                {opcao === "info" ? (<>
                
                    <form>
                        <table>
                            <tr>
                                <td>
                                    <div class="input-group mb-3">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Cientifico</button>
                                        <input type="text" name="nomeCientifico" value={dataPost.nomeCientifico} onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="input-group mb-3">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
                                        <input type="text" name="nomePopular" value={dataPost.nomePopular} onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="input-group mb-3">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Orientações</button>
                                        <input type="text" name="instrucoes" value={dataPost.instrucoes} onChange={handleChanage} class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><button type="submit" onClick={handleClick} class="btn btn-success">Salvar</button></td>
                            </tr>
                        </table>
                    </form>
                
                </>): (<>
                
                    <form>
                        <table>
                            <tr>
                                <td>
                                    <div class="input-group mb-3">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Cientifico</button>
                                        <input type="text"  value={dataPost.nomeCientifico}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group mb-3">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Nome Popular</button>
                                        <input type="text"  value={dataPost.nomePopular}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="input-group mb-3">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Orientações</button>
                                        <input type="text" value={dataPost.instrucoes}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group mb-3">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Localização Atual</button>
                                        <input type="text" value={data.localizacao}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group mb-3">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Área Atual</button>
                                        <input type="text" value={data.areaPlantio}  class="form-control" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <select 
                                        class="form-select" 
                                        aria-label="Default select example"
                                        name="areaId"
                                        value={dataPut.areaId || ''}
                                        onChange={handleChanagePut}
                                     >
                                    {dadosGetAreas ? (<>
                                        <option value="">Selecione uma área</option>
                                            {dadosGetAreas.map((data, i)=>{return(<> 
                                            <option key={data.id} value={data.id}>{data.nomeIdentificador}</option>
                                            </>)})}
                                            </>) : (<></>)}
                                    </select>   
                                </td>
                                <td>
                                    <select 
                                        class="form-select" 
                                        aria-label="Default select example"
                                        name="localizacaoId"
                                        value={dataPut.localizacaoId || ''}
                                        onChange={handleChanagePut}
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
                                <td>
                                    <select 
                                        class="form-select" 
                                        aria-label="Default select example"
                                        name="blocoId"
                                        value={dataPut.blocoId || ''}
                                        onChange={handleChanagePut}
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
                            <br/><br/><br/>
                            <tr>
                                <td><button type="submit" onClick={handleClickPut} class="btn btn-success">Salvar</button></td>
                            </tr>
                        </table>
                    </form>
                </>)}
            </div>
        </div>
    </>)
}

export default EditarInfo