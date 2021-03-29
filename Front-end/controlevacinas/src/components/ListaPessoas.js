import { Component } from "react";
import { Table, Button, message } from 'antd';
import PessoaDataService from "../services/PessoaDataService";

const {Column} = Table;
export default class ListaPessoas extends Component{
    
    constructor(props){
        super(props)
        this.state = {
            pessoas: [],
            message: null
        }
    }

    componentDidMount(){
        this.refreshPessoas();
    }

    refreshPessoas(){
        PessoaDataService.retriveAllPessoas()
            .then(
                response => {
                    console.log(response);
                    this.setState({pessoas: response.data})
                }
            )
    }

    success = (record) => {
        if(record.vacinada){
            record.vacinada = false;
        }else record.vacinada = true;
        PessoaDataService.updatePessoa(record, record.codigo);

        message.success('Status alterado com sucesso!');

    }

    render(){
        return(
            <div className="container">
                <h2> PESSOAS CADASTRADAS </h2>
                <div className="container">
                    <Table dataSource={this.state.pessoas}>
                        <Column title="NOME" dataIndex="nome" key="nome"/>
                        <Column title="CPF" dataIndex="cpf" key="cpf"/>
                        <Column title="TELEFONE" dataIndex="telefone" key="telefone"/>
                        <Column title="EMAIL" dataIndex="email" key="email"/>
                        <Column title="IDADE" dataIndex="idade" key="idade"/>
                        <Column title="DATA DE NASC." dataIndex="datanascimento" key="datanascimento"/>
                        <Column title="VACINADA" dataIndex="vacinada" key="vacinada" render={(text, record) => (<p>{String(record.vacinada)}</p>)}/>  
                        <Column title="ATUALIZAR" key="atualizar" render={(text, record) => (<Button onClick={() => this.success(record)} type="primary">Alterar Status</Button>)} />                         
                        

                    </Table>

                </div>
                
            </div>
        )

    }




}