type props = {
  visited: boolean;
};

export default function AboutUsSection({ visited }: props) {

  return (
    <section id="aboutus" data-scroll-section>
      <div className="pt-100 pb-40 px-20 h-300 place-items-end">
        <div className="text-black text-right max-w-250 min-h-150">
          <p
            className={`font-medium text-7xl transition ease-in-out duration-700 ${visited ? `opacity-100 translate-x-0` : `opacity-0 -translate-x-10`}`}
          >
            We are Shoplify! Giving out convenient products to our customers!
          </p>
          <p
            className={`mt-8 font-light text-xl transition ease-in delay-150 duration-700 ${visited ? `opacity-100` : `opacity-0`}`}
          >
            With us, you will surely find what you're looking for!
          </p>
        </div>
      </div>
    </section>
  );
}
