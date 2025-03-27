import '../../CSS/BodyStyle.css'

function RelatorioPlanta({data}) {
    return(<>
            <div class="card">
                <div class="card-body">
                   <h1 className='infoTitulo'>{data.nomePopular}</h1>
                   <br/>
                   <p className='infoItem'>Nome Cientifico:  <span className='infoTexto'>{data.nomeCientifico}</span></p>
                   <p className='infoItem'>Nome Popular:  <span className='infoTexto'>{data.nomePopular}</span></p>
                   <p className='infoItem'>Código:  <span className='infoTexto'>{data.codigo}</span></p>
                   <p className='infoItem'>Área plantio:  <span className='infoTexto'>{data.areaPlantio}</span></p>
                   <p className='infoItem'>Localização:  <span className='infoTexto'>{data.localizacao}</span></p>
                   <p className='infoItem'>Fase Atual:  <span className='infoTexto'>{data.faseAtual}</span></p>
                   <p className='infoItem'>Data de Plantio:  <span className='infoTexto'>{data.inicioCiclo}</span></p>
                   <p className='infoItem'>Ultima Adubação :  <span className='infoTexto'>{data.adubacao}</span></p>
                   <p className='infoItem'>notificações:</p>
                   {data.notificacoes !== null ? (<>
                        {data.notificacoes.map((data, i)=>{return(<>
                            <span className='infoTexto'>{data}</span>
                            <br/>      
                        </>)})}
                   </>) : (<></>)} 
                </div>
            </div> 
    </>)
};

export default  RelatorioPlanta;