import { useEffect, useState, type SetStateAction } from "react";
import { useProducts } from "../../../store/hooks/useProductStore";
import { useProductStore } from "../../../store/useProductStore";
import ProductBox from "../../ProductBox";

type props = {
  visited: boolean;
};

export default function CatalogSection({ visited }: props) {
  const products = useProductStore((state) => state.products);
  const { fetchProducts } = useProducts();

  useEffect(() => {
    fetchProducts();
  }, []);

  return (
    <section id="catalog" data-scroll-section>
      <div className="bg-gray-200 text-center py-20 min-h-150">
        <p className="text-black font-bold text-2xl">Items Showcase</p>
        <p>Check out the items distributed by our community</p>
        <div className="grid grid-cols-5">
          {products && products.map((p) => <ProductBox key={p.id} {...p} />)}
        </div>
      </div>
    </section>
  );
}
