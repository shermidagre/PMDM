---
title: Máquina de Café
---
stateDiagram-v2

    [*] --> Inicio

    state Inicio {
        [*] --> Pedido
    }

    Pedido --> ConLeche: "Con leche"
    Pedido --> ConAzucar: "Con azúcar"
    Pedido --> Solo: "Solo"
    Pedido --> ConAmbos: "Con azúcar y leche"

    ConLeche --> [*]: Entregando
    ConAzucar --> [*]: Entregando
    Solo --> [*]: Entregando
    ConAmbos --> [*]: Entregando