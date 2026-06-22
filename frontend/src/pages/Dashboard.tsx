import { useEffect, useState } from "react";
import { useToast } from "../ToastProvider";
import HomeSection from "../components/sections/Dashboard/HomeSection";
import CatalogSection from "../components/sections/Dashboard/CatalogSection";
import AboutUsSection from "../components/sections/Dashboard/AboutUsSection";
import ContactUsSection from "../components/sections/Dashboard/ContactUsSection";
import NavigationHeader from "../components/NavigationHeader";
import DebugAddProductModal from "../components/modals/Debug/DebugAddProduct";
import ObserverHitBox from "../components/debugComponents/ObserverHitBox";

type DebugOptions = {
  addProductModal: boolean;
  showObserverHitbox: boolean;
};

export default function Dashboard() {
  const [activeSection, setActiveSection] = useState<string>("");
  const [sectionVisited, setSectionVisited] = useState<Array<string>>([]);
  const rootMarginSample = "-90% 0px -10% 0px";

  useEffect(() => {
    const observerOptions = {
      root: null, // uses the browser window viewport
      rootMargin: rootMarginSample,
      threshold: 0,
    };

    const handleIntersection = (entries: IntersectionObserverEntry[]) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          if (!sectionVisited.includes(entry.target.id)) {
            setSectionVisited((prev) => [...prev, entry.target.id]);
          }
          setActiveSection(entry.target.id);
        }
      });
    };
    const observer = new IntersectionObserver(
      handleIntersection,
      observerOptions,
    );
    const sections = document.querySelectorAll("[data-scroll-section]");
    sections.forEach((section) => observer.observe(section));
    return () => observer.disconnect();
  }, []);

  //DEBUG STATE
  const [showDebugAddProduct, setShowDebugAddProduct] = useState<DebugOptions>({
    addProductModal: false,
    showObserverHitbox: false,
  });
  const toast = useToast();

  //Debug Show Add Modal
  useEffect(() => {
    (window as any).showDebugAddProduct = (arg: boolean) =>
      setShowDebugAddProduct((prev) => {
        return { ...prev, addProductModal: arg };
      });

    return () => {
      delete (window as any).showDebugModal;
    };
  }, []);

  return (
    <>
      {showDebugAddProduct.showObserverHitbox && (
        <ObserverHitBox
          rootMargin={rootMarginSample}
          activeSection={activeSection}
        />
      )}
      <NavigationHeader activeSection={activeSection} />
      <DebugAddProductModal
        show={showDebugAddProduct.addProductModal}
        onClose={() =>
          setShowDebugAddProduct((prev) => {
            return { ...prev, addProductModal: false };
          })
        }
      />
      <HomeSection visited={sectionVisited.includes("home")} />
      <CatalogSection visited={sectionVisited.includes("catalog")} />
      <AboutUsSection visited={sectionVisited.includes("aboutus")} />
      <ContactUsSection visited={sectionVisited.includes("contactus")} />
    </>
  );
}
