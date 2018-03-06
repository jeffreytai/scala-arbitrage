package slack

import java.util.Properties

import allbegray.slack.SlackClientFactory
import allbegray.slack.`type`.Payload

class SlackWebhook(username: String) {

  val props = new Properties()
  props.load(this.getClass.getClassLoader.getResourceAsStream("slack.properties"))

  val webhookClient = SlackClientFactory.createWebhookClient(props.getProperty("webhook-url"))

  def sendMessage(message: String): Unit = {
    val payload = new Payload
    payload.setUsername(username)
    payload.setText(message)

    webhookClient.post(payload)
  }

  def shutdown(): Unit = {
    webhookClient.shutdown()
  }

}
