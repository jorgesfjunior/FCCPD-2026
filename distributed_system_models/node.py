import socket
import threading
import time
import random
import sys
from config import NODES, CENTRAL_NODE, GROUPS

node_id = sys.argv[1]
mode = sys.argv[2]

host, port = NODES[node_id]

def send_message(target, message):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect(NODES[target])
        s.send(message.encode())
        s.close()
    except:
        print(f"[{node_id}] Falha ao enviar para {target}")

def server():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((host, port))
    s.listen()

    print(f"[{node_id}] Rodando em {host}:{port} ({mode})")

    while True:
        conn, addr = s.accept()
        data = conn.recv(1024).decode()
        print(f"[{node_id}] Recebeu: {data}")
        conn.close()

def generate_metric():
    return random.randint(0, 100)

def centralized_behavior():
    while True:
        metric = generate_metric()

        if node_id != CENTRAL_NODE:
            send_message(CENTRAL_NODE, f"{node_id}:{metric}")
        else:
            print(f"[{node_id}] Processando dados centralizados")

        time.sleep(2)

def decentralized_behavior():
    while True:
        metric = generate_metric()

        # Se for líder
        if node_id in GROUPS:
            print(f"[{node_id}] Líder processando grupo")

        else:
            # encontrar líder
            leader = None
            for l, members in GROUPS.items():
                if node_id in members:
                    leader = l

            if leader:
                send_message(leader, f"{node_id}:{metric}")

        time.sleep(2)

def distributed_behavior():
    while True:
        metric = generate_metric()

        # envia para todos os outros nós
        for target in NODES:
            if target != node_id:
                send_message(target, f"{node_id}:{metric}")

        print(f"[{node_id}] Broadcast para todos")

        time.sleep(2)

# Thread servidor
threading.Thread(target=server, daemon=True).start()

# Escolher comportamento
if mode == "centralized":
    centralized_behavior()
elif mode == "decentralized":
    decentralized_behavior()
elif mode == "distributed":
    distributed_behavior()
else:
    print("Modo inválido")