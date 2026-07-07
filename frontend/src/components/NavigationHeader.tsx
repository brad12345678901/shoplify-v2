import ShoplifyLogo from "../assets/shoplify logo text.png";
import { MdAccountCircle } from "react-icons/md";
import Button from "./Button";
import { useState } from "react";
import LoginForm from "./modals/Dashboard/LoginForm";
import FormInput from "./FormInput";

type props = {
  activeSection: string;
};

type Modals = {
  loginModal: boolean;
};

export default function NavigationHeader({ activeSection }: props) {
  const [modal, setModal] = useState<Modals>({ loginModal: false });

  const getBlockByActiveSection = (section: string) => {
    switch (section) {
      case "home":
        return "center";
      case "aboutus":
        return "center";
      default:
        return "end";
    }
  };

  const handleNavClick = (id: string) => {
    const element = document.getElementById(id);
    if (element) {
      const block = getBlockByActiveSection(id);
      element.scrollIntoView({ behavior: "smooth", block: block });
    }
  };

  const toggleLoginModal = () => {
    setModal((prev) => {
      return {
        ...prev,
        loginModal: !modal.loginModal,
      };
    });
  };

  function loginFormSubmitHandler(e) {
    e.preventDefault();
  }

  return (
    <>
      {modal.loginModal && (
        <LoginForm open={modal.loginModal} toggleModal={toggleLoginModal}>
          <h2 className="font-bold">Login Form</h2>
          <hr />
          <form onSubmit={loginFormSubmitHandler}>
            <FormInput label="Email" />
            <FormInput label="Password" type="password" />
            <Button type="submit">Submit</Button>
          </form>
        </LoginForm>
      )}
      <div className="fixed top-0 z-10 rounded-b-sm bg-white flex w-full min-h-20 px-20 items-center justify-between">
        <img className="w-80 h-20 place-self-center" src={ShoplifyLogo} />
        <nav className="pl-5 flex flex-row flex-wrap gap-x-10">
          <ul
            onClick={() => handleNavClick("home")}
            className={`cursor-pointer ${activeSection == "home" && "font-bold"}`}
          >
            Home
          </ul>
          <ul
            onClick={() => handleNavClick("catalog")}
            className={`cursor-pointer ${activeSection == "catalog" && "font-bold"}`}
          >
            Catalog
          </ul>
          <ul
            onClick={() => handleNavClick("aboutus")}
            className={`cursor-pointer ${activeSection == "aboutus" && "font-bold"}`}
          >
            About Us
          </ul>
          <ul
            onClick={() => handleNavClick("contactus")}
            className={`cursor-pointer ${activeSection == "contactus" && "font-bold"}`}
          >
            Contact
          </ul>
        </nav>
        <span
          className="flex flex-row cursor-pointer"
          onClick={toggleLoginModal}
        >
          <MdAccountCircle size={30} /> <p>Login</p>
        </span>
      </div>
    </>
  );
}
