import { useState, useEffect } from "react";

import Button from "./Button";
import Input from "./Input";
import Message from './Message/Message'

const endpoints = "http://localhost:7000/api/customers";

const CreateUser = props => {
  const [inputs, setInputs] = useState({
    nombre: { value: "", labelName: "Nombre" },
    direccion: { value: "", labelName: "Dirección" },
    numeroDocumento: { value: "", labelName: "Numero de documento" },
  });

  const [showMessage, setShowMessage] = useState(false);

  const changedValueHandler = (e, id) => {
    const updatedInputs = { ...inputs };
    const updatedElementForm = { ...updatedInputs[id] };

    updatedElementForm.value = e.target.value;
    updatedInputs[id] = updatedElementForm;

    setInputs(updatedInputs);
  };

  const submitedHandler = (e) => {
    e.preventDefault();

    const formData = {};

    for (let identifier in inputs) {
      formData[identifier] = inputs[identifier].value;
    }

    fetch("http://localhost:7000/api/customers", {
      method: "POST",
      body: JSON.stringify(formData),
    }).then((data) => {
      if(data.status === 201) {
        setShowMessage(true);

        setTimeout(() => {
          props.history.replace('/users');
        }, 1500);
      }
    });
  };

  const inputsElements = [];

  for (let key in inputs) {
    inputsElements.push({
      id: key,
      data: inputs[key],
    });
  }

  const inputsForm = inputsElements.map((input) => (
    <Input
      key={input.id}
      labelName={input.data.labelName}
      value={input.data.value}
      changed={(event) => changedValueHandler(event, input.id)}
    />
  ));

  const message = showMessage ? <Message /> : null;

  return (
    <section className="row justify-content-center">
      <form onSubmit={submitedHandler} className="col-6">
        {message}
        {inputsForm}

        <Button classes="btn-primary btn-lg btn-block">Crear</Button>
      </form>
    </section>
  );
};

export default CreateUser;
