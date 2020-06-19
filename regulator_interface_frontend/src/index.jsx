import React from 'react'
import {render} from 'react-dom'
import axios from "axios"

import './logic/data-transfer.js'
import {connect, disconnect} from './logic/data-transfer.js'
import {IO_control} from './components/io-control.jsx'
import * as mainStyles from './styles/main.less'


const getIOLabels = async () => {
    try {
        const response = await axios.post('./IOLabels')
        return response.data
    } catch (e) {
        console.log(`getIOLabels request failed: ${e}`)
        return []
    }
}

export class App extends React.Component {

    constructor(props) {
        super(props)
        this.state = []
        this.state.sysstate = []
        this.state.IOLabels = []

        getIOLabels().then(data => (
            this.setState((state) => {
                state.IOLabels = data
                return state
            })
        ))
    }

    componentDidMount() {
        document.getElementById('disconnect').setAttribute("disabled", "1")
    }


    render() {
        return (
            <div>
                <div id="connectionControl">
                    <div className="info">Блок для подключения к АРМ</div>
                    <div id="connect" onClick={() => connect(this)}>Подключиться</div>
                    <div id="disconnect" onClick={() => disconnect(this)}>Отключиться</div>
                </div>

                <IO_control IOLabels={this.state.IOLabels}
                            sysstate={this.state.sysstate} />
            </div>
        )
    }
}

render(<App/>, document.getElementById('app'))
