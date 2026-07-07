import { createPortal } from "react-dom";

type LoginProps = {
  open: boolean;
  toggleModal: () => void;
  children: any;
};

export default function LoginForm({ open, toggleModal, children }: LoginProps) {
  const modalRoot = document.getElementById("modal");
  if (!modalRoot) return null;

  return createPortal(
    <div
      className="flex fixed inset-0 w-full h-full z-50 bg-black/80 justify-center items-center"
      onClick={toggleModal}
    >
      <div
        className="bg-white w-1/4 min-h-100 h-[80vh] max-h-200 rounded-2xl p-4"
        onClick={(e) => e.stopPropagation()}
      >
        {children}
      </div>
    </div>,
    modalRoot,
  );
}
