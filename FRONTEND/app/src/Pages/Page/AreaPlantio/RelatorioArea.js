import '../../CSS/BodyStyle.css'

function RelatorioArea({data})
{
    return(<>
        <div class="card">
          <div class="card-body">
            <h1 className='infoTitulo'>{data.nomeIdentificador}</h1>
            <br/>
            <p className='infoItem'>
              Contendo <span className='infoTexto'>{data.dimensao}</span>, {data.localizacoes ? data.localizacoes.length : 0} locais cadastrados, 
              dentre eles {data.localizacoes ? data.localizacoes.filter(loc => loc.disponivel).length : 0} disponíveis neste momento.
            </p>        
            <p className='infoItem'>Notificações:</p>
            {data.notificacoes != null ?(<>
                {data.notificacoes.map((data, i)=>{return(<>
                    <span className='infoTexto' key={i}>{data}</span>
                    <br/>
                </>)})}
            </>) : (<></>)}
          </div>
        </div> 
    </>)
};

export default RelatorioArea;