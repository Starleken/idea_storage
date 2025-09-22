import { Button } from "@/shared/ui/kit/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/shared/ui/kit/form";
import { Input } from "@/shared/ui/kit/input";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { useSignup } from "../model/use-signup";

const signupSchema = z
  .object({
    email: z.email("Email имеет неправильный формат"),
    password: z
      .string({
        message: "Пароль обязательное поле",
      })
      .min(5, "Пароль должен быть больше 5 символов"),
    confirmPassword: z
      .string({ message: "Подтвердите пароль" })
      .min(5, "Пароль должен быть больше 5 символов"),
  })
  .refine((data) => data.confirmPassword === data.password, {
    message: "Пароли не совпадают",
    path: ["confirmPassword"],
  });

export function SignupForm() {
  const form = useForm({
    resolver: zodResolver(signupSchema),
  });
  const { signup, isPending, errorMessage } = useSignup();

  const onSubmit = form.handleSubmit(signup);

  return (
    <Form {...form}>
      <form className="flex flex-col gap-4" onSubmit={onSubmit}>
        <FormField
          control={form.control}
          name="email"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Email</FormLabel>
              <FormControl>
                <Input type="email" placeholder="user@gmail.com" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="password"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Пароль</FormLabel>
              <FormControl>
                <Input type="password" placeholder="*********" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="confirmPassword"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Повторите пароль</FormLabel>
              <FormControl>
                <Input type="password" placeholder="*********" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        {errorMessage && <p className="text-destructive">{errorMessage}</p>}
        <Button disabled={isPending} type="submit">
          Зарегистрироваться
        </Button>
      </form>
    </Form>
  );
}
