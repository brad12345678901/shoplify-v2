type props = {
    rootMargin: string,
    activeSection: string,
}

export default function ObserverHitBox({rootMargin, activeSection} : props) {
  const rootMarginArray = rootMargin.split(" ");

  return (
    <div className="fixed inset-0 pointer-events-none z-50 flex flex-col justify-between">
      {/* Top Dead Zone: Physically takes up 20% of viewport height */}
      <div
        className="bg-red-500/10 border-b border-red-500 w-full flex items-end justify-end p-2 text-red-500 text-xs font-mono"
        style={{ height: rootMarginArray[0].replaceAll('-', '') }}
      >
        Top Dead Zone ({rootMarginArray[0]}%)
      </div>

      {/* ACTIVE HITBOX ZONE: Dynamically occupies the remaining 60% */}
      <div className="w-full h-full flex items-center justify-center border-2 border-dashed border-yellow-500 bg-yellow-500/5 font-mono text-sm text-yellow-500">
        ACTIVE COLLISION ZONE (Takes up {100 -  1* 2}% of the
        screen) CURRENTLY ACTIVE: <span className="text-green-400 font-black">{activeSection || "none"}</span>
      </div>

      {/* Bottom Dead Zone: Physically takes up 20% of viewport height */}
      <div
        className="bg-red-500/10 border-t border-red-500 w-full flex items-start justify-end p-2 text-red-500 text-xs font-mono"
        style={{ height: rootMarginArray[2].replaceAll('-', '') }}
      >
        Bottom Dead Zone ({rootMarginArray[2]}%)
      </div>
    </div>
  );
}
