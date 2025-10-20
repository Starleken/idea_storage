import { useEffect, useState } from "react";

export function useDebouncedValue<T>(value: T, delay: number) {
  const [debounce, setDebounce] = useState(value);

  useEffect(() => {
    const timeout = setTimeout(() => {
      setDebounce(value);
    }, delay);

    return () => clearTimeout(timeout);
  }, [value, delay]);

  return debounce;
}
