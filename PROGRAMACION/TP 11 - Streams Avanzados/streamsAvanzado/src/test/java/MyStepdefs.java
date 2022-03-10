import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @Given("^un cliente con saldo$")
    public void unClienteConSaldo() {

    }

    @And("un pedido de ese cliente")
    public void unPedidoDeEseCliente() {
    }

    @When("cobrar el pedido")
    public void cobrarElPedido() {
    }

    @Then("el pedido pasa a estado confirmado")
    public void elPedidoPasaAEstadoConfirmado() {
        assert(false);
    }

    @Given("un cliente sin saldo sufiente para el pedido")
    public void unClienteSinSaldoSufienteParaElPedido() {
    }

    @Then("el pedido pasa a estado rechazado")
    public void elPedidoPasaAEstadoRechazado() {
    }

    @Given("un pedido confirmado")
    public void unPedidoConfirmado() {
    }

    @When("distruibir pedidos")
    public void distruibirPedidos() {
    }

    @Then("el pedido pasa a estado en distribucion")
    public void elPedidoPasaAEstadoEnDistribucion() {
    }

    @Given("un pedido en distribucion")
    public void unPedidoEnDistribucion() {
    }

    @When("entregar pedidos")
    public void entregarPedidos() {
    }

    @Then("el pedido pasa a estado entregado")
    public void elPedidoPasaAEstadoEntregado() {
    }

    @Given("un pedido rechazado")
    public void unPedidoRechazado() {
    }

    @Then("el pedido sigue en estado rechazado")
    public void elPedidoSigueEnEstadoRechazado() {
    }
}
