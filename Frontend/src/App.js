import 'bootstrap/dist/css/bootstrap.min.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home'
import Model from './pages/Model'
import AddMakeForm from './pages/AddMakeForm';
import AddModelForm from './pages/AddModelForm';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/car/:carMake" element={<Model />} />
          <Route path="/addMake" element={<AddMakeForm />} />
          <Route path="/car/:carMake/addModel" element={<AddModelForm />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;