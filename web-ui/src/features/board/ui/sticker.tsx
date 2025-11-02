import clsx from "clsx";
import { useLayoutEffect, useRef, useState, type Ref } from "react";
import type React from "react";

export function Sticker({
  text,
  ref,
  id,
  x,
  y,
  onClick,
  isSelected,
  isEditing,
  onTextChange,
}: {
  ref: Ref<HTMLButtonElement>;
  id: string;
  text: string;
  x: number;
  y: number;
  isSelected?: boolean;
  isEditing?: boolean;
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
  onTextChange?: (text: string) => void;
}) {
  return (
    <button
      data-id={id}
      ref={ref}
      className={clsx(
        "absolute bg-yellow-300 px-2 py-4 rounded-xs shadow-md text-left",
        isSelected && "outline outline-2 outline-blue-500",
      )}
      style={{ transform: `translate(${x}px, ${y}px)` }}
      onClick={onClick}
    >
      <TextareaAutoSize
        value={text}
        onChange={onTextChange}
        isEditing={isEditing}
      />
    </button>
  );
}

function TextareaAutoSize({
  value,
  onChange,
  isEditing,
}: {
  isEditing?: boolean;
  value: string;
  onChange?: (value: string) => void;
}) {
  const [width, setWidth] = useState(0);
  const [height, setHeight] = useState(0);
  const ref = useRef<HTMLDivElement>(null);
  useLayoutEffect(() => {
    if (!ref.current) return;
    const { scrollWidth, scrollHeight } = ref.current;
    setWidth(scrollWidth);
    setHeight(scrollHeight);
  }, [value]);
  const gap = 2;
  return (
    <div className="relative">
      <div
        ref={ref}
        className={clsx("whitespace-pre-wrap", isEditing && "opacity-0")}
      >
        {value}
      </div>
      {isEditing && (
        <textarea
          className="absolute left-0 top-0 resize-none overflow-hidden focus:outline-none"
          value={value}
          autoFocus={true}
          onChange={(e) => onChange?.(e.target.value)}
          style={{
            width: width + gap,
            height: height + gap,
          }}
        />
      )}
    </div>
  );
}
