import '../../CSS/BodyStyle.css'

function RelatorioArea({data})
{
    return(<>
        <div class="card">
          <div class="card-body">
            <h1 className='infoTitulo'>{data.nomeIdentificador}</h1>
            <br/>
            <p className='infoItem'>GPS: <span className='infoTexto'>{data.gps}</span></p>
            <p className='infoItem'>Dimensão: <span className='infoTexto'>{data.dimensao}</span></p>        
            <p className='infoItem'>Quantidade de Sub Áreas: <span className='infoTexto'>{data.tamanhoMax} sendo {data.eixoX} no eixo X por {data.eixoY} no eixo Y</span></p>        

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