const amqp = require('amqplib');

const QUEUE = 'pedidos';

async function consume() {
  const conn = await amqp.connect('amqp://localhost');
  const channel = await conn.createChannel();

  await channel.assertQueue(QUEUE);

  console.log("Aguardando pedidos...");

  channel.consume(QUEUE, (msg) => {
    const pedido = JSON.parse(msg.content.toString());

    console.log("Processando pedido:", pedido);

    // Simula processamento lento
    setTimeout(() => {
      console.log("Pedido processado:", pedido.id);
      channel.ack(msg);
    }, 3000);

  });
}

consume();