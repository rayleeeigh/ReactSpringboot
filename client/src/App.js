import { BrowserRouter, Switch, Route } from "react-router-dom";
import Index from "./pages/index";

function App() {
  return (
    <BrowserRouter>
        <Switch>
          <Route path="/" component={Index} exact />
        </Switch>
      </BrowserRouter>
  );
}

export default App;
