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
import { useLogin } from "../model/use-login";

const loginSchema = z.object({
  email: z.email("Email имеет неправильный формат"),
  password: z
    .string({
      message: "Пароль обязательное поле",
    })
    .min(5, "Пароль должен быть больше 5 символов"),
});

export function LoginForm() {
  const form = useForm({
    resolver: zodResolver(loginSchema),
  });
  const { login, isPending, errorMessage } = useLogin();

  const onSubmit = form.handleSubmit(login);

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
        {errorMessage && <p className="text-destructive">{errorMessage}</p>}
        <Button disabled={isPending} type="submit">
          Авторизоваться
        </Button>
      </form>
    </Form>
  );
}
