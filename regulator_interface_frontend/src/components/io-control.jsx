import React from 'react'

import {sendInputState} from '../logic/data-transfer.js'
import * as mainStyles from '../styles/main.less'

export class IO_control extends React.Component {

    constructor(props) {
        super(props)
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
    }

    componentDidMount() {
    }

    handleSetState(inputId, inputState) {
        this.props.sysstate['inputs'][inputId] = inputState
        sendInputState(this.props.sysstate['inputs'])
    }

    render() {
        return (
            <div id="IOControl">
                <div className="info">Контроль системы водяного электрического отопления</div>
                <div id="outputsControl">
                    {
                        (this.props.sysstate['inputs'] != null && this.props.IOLabels != null) ?
                            <div className="info">Контроль состояния выходов</div>: ""
                    }
                    {
                        (this.props.sysstate['outputs'] != null && this.props.IOLabels != null) ?
                            Object.entries(this.props.sysstate['outputs']).map(
                                ([key, value]) => {
                                    return (
                                        <div className={"outputLabel " + (value ? 'green' : 'red')}>
                                            {this.props.IOLabels.outputs[parseInt(key)]}:<br/>{value ? 'ВКЛ' : 'ВЫКЛ'}
                                        </div>
                                    )
                                }
                            ) : ""
                    }
                </div>
                <div id="inputsControl">
                    {
                        (this.props.sysstate['inputs'] != null && this.props.IOLabels != null) ?
                            <div className="info">Контроль состояния входов</div> : ""
                    }
                    {
                        (this.props.sysstate['inputs'] != null && this.props.IOLabels != null) ?
                            Object.entries(this.props.sysstate['inputs']).map(
                                ([key, value]) => {
                                    return (
                                        <div onMouseDown={() => this.handleSetState(key, true)}
                                             onMouseUp={() => this.handleSetState(key, false)}
                                             className="inputControlButton">
                                            {this.props.IOLabels.inputs[parseInt(key)]}
                                        </div>
                                    )
                                }
                            ) : ""
                    }
                </div>
            </div>
        )
    }
}