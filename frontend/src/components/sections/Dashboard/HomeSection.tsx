type props = {
  visited: boolean,
};

export default function HomeSection({ visited }: props) {
  return (
    <>
      <section id="home" data-scroll-section>
        <div className="custom_section_1 pt-120 pb-40 px-20 h-320 min-h-150">
          <div className="text-white max-w-250">
            <p
              className={`font-medium text-7xl transition ease-in-out duration-700 ${visited ? `opacity-100 translate-x-0` : `opacity-0 -translate-x-10`}`}
            >
              Shop with us! Lift yourself with Products you deserve!
            </p>
            <p
              className={`mt-8 font-light text-xl transition ease-in delay-150 duration-700 ${visited ? `opacity-100` : `opacity-0`}`}
            >
              With just one click of a button, All you desired items will be on
              your possession!
            </p>
          </div>
        </div>
      </section>
    </>
  );
}
