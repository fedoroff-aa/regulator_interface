import SockJS from "sockjs-client"
import Stomp from "stompjs"

let stompClient = null

function setConnected(connected) {
    document.getElementById('connect').setAttribute("disabled", connected ? '1' : '0')
    document.getElementById('disconnect').setAttribute("disabled", !connected ? '1' : '0')
}

export const connect = (main_context) => {
    let socket = new SockJS('/sysstate')
    stompClient = Stomp.over(socket)
    stompClient.debug = null
    stompClient.connect({}, function (frame) {
        setConnected(true)
        stompClient.subscribe('/topic/sysstate', function (PLC_state_from_server) {
            main_context.setState((state) => {
                state.sysstate = JSON.parse(PLC_state_from_server.body)
                return state
            })
        })
    })
}

export const disconnect = (main_context) => {
    if (stompClient != null) {
        stompClient.disconnect()
    }
    setConnected(false)
    main_context.setState((state) => {
        state.sysstate = []
        return state
    })
}

export const sendInputState = (inputsState) => {
    stompClient.send("/sysstate", {}, JSON.stringify({'inputs': inputsState}))
}
