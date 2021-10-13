import { BrowserRouter, Switch, Route } from "react-router-dom";
import Index from "./pages/index";
import Enroll from "./pages/enrollSubjects";

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" component={Index} exact />
        <Route exact path="/enroll">
          <Enroll />
        </Route>
      </Switch>
    </BrowserRouter>
  );
}

export default App;
