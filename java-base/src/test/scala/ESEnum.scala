import org.junit.Test

/**
  * @author WenTingTing by 2020/9/15
  */
class ESEnum {

  object EsConfigEvents extends Enumeration {
    type EsConfigEvents = Value
    // server_config 开服时间
    val Server_Config = Value("server==Config")
    val tt = Value
  }

  @Test
  def test: Unit = {
    println(EsConfigEvents.Server_Config.toString)
    println(EsConfigEvents.Server_Config.getClass)
   // println(EsConfigEvents.tt)

  }
}
