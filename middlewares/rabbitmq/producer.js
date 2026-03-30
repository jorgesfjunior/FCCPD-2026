const amqp = require('amqplib');
const express = require('express');

const app = express();
app.use(express.json());

const QUEUE = 'pedidos';

async function sendMessage(msg) {
  const conn = await amqp.connect('amqp://localhost');
  const channel = await conn.createChannel();

  await channel.assertQueue(QUEUE);

  channel.sendToQueue(QUEUE, Buffer.from(JSON.stringify(msg)));

  console.log("📤 Pedido enviado:", msg);
}

app.post('/pedido', async (req, res) => {
  await sendMessage(req.body);
  res.send({ status: "Pedido enviado para fila!" });
});

app.listen(3000, () => console.log("API rodando na porta 3000"));