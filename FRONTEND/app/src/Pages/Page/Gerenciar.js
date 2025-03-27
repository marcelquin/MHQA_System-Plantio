import '../CSS/reset.css'
import '../CSS/BodyStyle.css'
import React, { useState, useEffect } from 'react';
import Gerencia_AreaPlantio from './AreaPlantio/Gerencia_AreaPlantio';
import Gerencia_SubareaPlantio from './Subarea/Gerencia_SubareaPlantio';
import Gerencia_Planta from './Planta/Gerencia_Planta';
import Cad_AreaPlantio from './AreaPlantio/Cad_AreaPlantio';
import Cad_SubareaPlantio from './Subarea/Cad_SubareaPlantio';
import Cad_Planta from './Planta/Cad_Planta';
import AdubacaoGeral from './AreaPlantio/AdubacaoArea';
import AdubacaoIndividual from './Subarea/AdubacaoSubArea';

function Gerenciar() {
  const [seletorOpcao, setseletorOpcao] = useState('')
  const setconteudo = (valor) => {
    if (valor > 10) {
      return <p>O valor é maior que 10.</p>;
    } else {
      return <p>O valor é menor ou igual a 10.</p>;
    }
  };

  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState(null);

  const handleOpenModal = (content) => {
      setModalContent(content);
      setShowModal(true);
  };

  return (
    <div className='boxAdicionarGerenciar'>
      <div className='submenu'>
        <table>
          <tr>
            <td className='titulo'>Área plantio</td>
          </tr>
          <br/>
          <tr>
            <td className='opcao' onClick={() => setseletorOpcao("areaCad")}><a>Cadastrar</a></td>
          </tr>
          <tr>
            <td className='opcao' onClick={() => setseletorOpcao("areager")}><a>Gerenciar</a></td>
          </tr>
          <br/>
          <tr>
            <td className='titulo'>Subárea plantio</td>
          </tr>
          <br/>
          <tr>
            <td className='opcao' onClick={() => setseletorOpcao("subareaCad")}><a>Cadastrar</a></td>
          </tr>
          <tr>
            <td className='opcao' onClick={() => setseletorOpcao("subareaGer")}><a>Gerenciar</a></td>
          </tr>
          <br/>
          <tr>
            <td className='titulo'>Planta</td>
          </tr>
          <br/>
          <tr>
            <td className='opcao' onClick={() => setseletorOpcao("plantaCad")}><a>Cadastrar</a></td>
          </tr>
          <tr>
            <td className='opcao' onClick={() => setseletorOpcao("plantaGer")}><a>Gerenciar</a></td>
          </tr>
        </table>
      </div>
        {seletorOpcao === "areaCad" ? (<div className='conteudo'><Cad_AreaPlantio/></div>) : ("")}
        {seletorOpcao === "areager" ? (<div className='conteudo'><Gerencia_AreaPlantio/></div>) : (<p></p>)}
        {seletorOpcao === "subareaCad" ? (<div className='conteudo'><Cad_SubareaPlantio/></div>)  : (<p></p>)}
        {seletorOpcao === "subareaGer" ? (<div className='conteudo'><Gerencia_SubareaPlantio/></div>)  : (<p></p>)}
        {seletorOpcao === "plantaCad" ?  (<div className='conteudo'><Cad_Planta/> </div>):  ("")}
        {seletorOpcao === "plantaGer" ? (<div className='conteudo'><Gerencia_Planta/></div>)  : (<p></p>)}
    </div>    
  );
}

export default Gerenciar; 