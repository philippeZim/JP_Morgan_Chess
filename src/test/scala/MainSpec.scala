import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class MainSpec extends AnyWordSpec{
  "Main" should {
    "return starting chess board" in {
        main() should be (());
    }
  }
}
